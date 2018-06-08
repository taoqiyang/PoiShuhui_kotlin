package com.flying.xiaopo.poishuhui_kotlin.domain.network

interface Source<out T, in P> {
  fun fetch(param: P): T
}