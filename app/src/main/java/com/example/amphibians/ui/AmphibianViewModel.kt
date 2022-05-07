/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.amphibians.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class AmphibianApiStatus {LOADING, ERROR, DONE}

class AmphibianViewModel : ViewModel() {

    private var _status = MutableLiveData<AmphibianApiStatus> ()
    val status = _status
    private var _amphibianList = MutableLiveData<List<Amphibian>> ()
    val amphibianList = _amphibianList
    private var _selectedAmphiban = MutableLiveData<Amphibian> ()
    val selectedAmphiban = _selectedAmphiban

    init {
        getAmphibianList()
    }
    private fun getAmphibianList() {
        viewModelScope.launch {
            _status.value = AmphibianApiStatus.LOADING
            try {
                _amphibianList.value = AmphibianApi.retrofitService.getAmphibians()
                _status.value = AmphibianApiStatus.DONE
            }
            catch (e :Exception) {
                _status.value = AmphibianApiStatus.ERROR
                _amphibianList.value = listOf()
            }
        }
    }

    fun onAmphibianClicked(amphibian: Amphibian) {
        _selectedAmphiban.value = amphibian
    }
}
