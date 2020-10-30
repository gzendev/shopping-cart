package com.garbarino.shoppingcart.ws.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public @Getter @AllArgsConstructor(access = AccessLevel.PRIVATE) enum CartStatus {
  
  NEW(0, "Nuevo"),
  PROCESSED(1, "Procesado"), 
  FAILED(2, "No Procesado"), 
  READY(3, "Listo");

  private Integer id;
  private String name;

  public static CartStatus getCartStatus(final Integer id) {
    if (id != null) {
      final List<CartStatus> values = Arrays.stream(CartStatus.values()).collect(Collectors.toList());
      for (final CartStatus cartStatus : values) {
        if (id.equals(cartStatus.getId())) {
          return cartStatus;
        }
      }
    }
    return null;
  }
}