package com.greenmiststudios.common.di.qualifiers

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

public enum class Threading: Qualifier {
  IO,
  MAIN,
  ;

  override val value: QualifierValue = name
}