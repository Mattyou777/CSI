package com.csi.testTask.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.val;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Price implements Comparable<Price> {

  @NonNull
  private Long id;
  @NonNull
  private String productCode;
  @NonNull
  private int number;
  @NonNull
  private int depart;
  @NonNull
  private Date begin;
  @NonNull
  private Date end;
  @NonNull
  private long value;

  public int compareTo(Price o) {
    int codeDif = productCode.compareTo(o.getProductCode());
    if (codeDif != 0) {
      return codeDif;
    }
    int numberDif = number - o.getNumber();
    if (numberDif != 0) {
      return numberDif;
    }
    int departDif = depart - o.getDepart();
    if (departDif != 0) {
      return departDif;
    }
    val beginDif = begin.compareTo(o.getBegin());
    if (beginDif != 0) {
      return beginDif;
    }
    val endDif = end.compareTo(o.getEnd());
    return endDif;

  }
}
