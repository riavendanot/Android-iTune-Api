package com.riavendanot.ituneapi.home

import androidx.lifecycle.*
import com.riavendanot.ituneapi.common.Resource
import com.riavendanot.ituneapi.common.constant.StringConstant.EMPTY_STRING
import com.riavendanot.ituneapi.common.extension.value
import com.riavendanot.ituneapi.domain.entity.ResultDto
import com.riavendanot.ituneapi.domain.usecase.base.UseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: UseCase<Pair<String, Int>, Resource<List<ResultDto>>>
): ViewModel() {

    private val _loaderFlag = MutableLiveData<Boolean>(false)
    val loader: LiveData<Boolean> get() = _loaderFlag

    private val _showDialog = MutableLiveData<Boolean>(false)
    val showDialog: LiveData<Boolean> get() = _showDialog

    private val _message = MutableLiveData<String>(EMPTY_STRING)
    val message : LiveData<String> get() = _message

    private val _list = MutableLiveData<List<ResultDto>?>(emptyList())
    val list: LiveData<List<ResultDto>?> get() = _list

    private val _resetList = MutableLiveData<Boolean>(false)
    val resetList: LiveData<Boolean> get() = _resetList

    private val query = MutableLiveData<String>(EMPTY_STRING)
    private val offset = MutableLiveData(0)

    fun searchTerm(query: String) {
        this.query.value = query
        this.offset.value = 20
        getData(false)
    }

    fun requestTerm() = getData(true)

    private fun getData(paging: Boolean) {
        viewModelScope.launch {
            _loaderFlag.value = true
            val result = try {
                useCase.buildUseCase(Pair(query.value.value(), offset.value.value()))
            } catch (e: Throwable) {
                Resource.Failure(e)
            }

            _loaderFlag.value = false
            when(result) {
                is Resource.Success<List<ResultDto>> -> {
                    if (!paging) {
                        _resetList.value = true
                    }
                    offset.value = offset.value.value() + 20
                    _list.value = result.data
                }
                is Resource.Failure -> {
                    if (!paging) {
                        _showDialog.value = true
                        _message.value = result.exception.message.value()
                    }
                }
            }
        }
    }

    fun changeDialogState() {
        _showDialog.value = false
    }

    fun changeResetList() {
        _resetList.value = false
    }
}