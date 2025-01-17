// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.ide;

import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.Nullable;

@Internal
public final class CliResult {
  public static final CliResult OK = new CliResult(0, null);

  public final int exitCode;
  public final @Nullable @NlsContexts.DialogMessage String message;

  public CliResult(int exitCode, @Nullable @NlsContexts.DialogMessage String message) {
    this.exitCode = exitCode;
    this.message = message;
  }

  @Override
  public String toString() {
    return message == null ? String.valueOf(exitCode) : exitCode + ": " + message;
  }
}
