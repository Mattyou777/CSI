package com.csi.testTask.services;

import com.csi.testTask.domain.Price;
import java.util.ArrayList;
import java.util.List;

public class CalculateService {

  public static List<Price> calculate(List<Price> oldPrices, List<Price> newPrices) {
    List<Price> calculatedPrices = new ArrayList<Price>(oldPrices);
    for (int i = 0; i < newPrices.size(); i++) {
      Price newPrice = newPrices.get(i);
      if (newPrice.getBegin().after(newPrice.getEnd())) {
        throw new RuntimeException("Unreachable date period");
      }
      for (int j = 0; j < calculatedPrices.size(); j++) {
        Price oldPrice = calculatedPrices.get(j);
        if (oldPrice.getBegin().after(oldPrice.getEnd())) {
          throw new RuntimeException("Unreachable date period");
        }
        if (oldPrice.getProductCode().equals(newPrice.getProductCode()) && oldPrice.getDepart() == newPrice.getDepart()
            && oldPrice.getNumber() == newPrice
            .getNumber()) {
          if (newPrice.getEnd().equals(oldPrice.getBegin())) {
            if (newPrice.getValue() == oldPrice.getValue()) {
              newPrice.setEnd(oldPrice.getEnd());
              calculatedPrices.remove(oldPrice);
              j--;
            }
            continue;
          }
          if (newPrice.getBegin().equals(oldPrice.getEnd())) {
            if (newPrice.getValue() == oldPrice.getValue()) {
              newPrice.setBegin(oldPrice.getBegin());
              calculatedPrices.remove(oldPrice);
              j--;
            }
            continue;
          }
          if (newPrice.getBegin().before(oldPrice.getEnd()) && newPrice.getEnd().after(oldPrice.getBegin())) {
            if (newPrice.getBegin().after(oldPrice.getBegin())) {
              if (newPrice.getValue() == oldPrice.getValue()) {
                if (newPrice.getEnd().after(oldPrice.getEnd())) {
                  newPrice.setBegin(oldPrice.getBegin());
                  calculatedPrices.remove(oldPrice);
                  j--;
                  continue;
                }
              }
              if (newPrice.getEnd().before(oldPrice.getEnd())) {
                calculatedPrices.add(
                    new Price(newPrice.getId(), newPrice.getProductCode(), newPrice.getNumber(), newPrice.getDepart(),
                        newPrice.getEnd(), oldPrice.getEnd(),
                        oldPrice.getValue()));
              }
              oldPrice.setEnd(newPrice.getBegin());
            } else {
              if (newPrice.getEnd().after(oldPrice.getEnd())) {
                calculatedPrices.remove(oldPrice);
                j--;
                continue;
              }
              if (newPrice.getValue() == oldPrice.getValue()) {
                newPrice.setEnd(oldPrice.getEnd());
                calculatedPrices.remove(oldPrice);
                j--;
                continue;
              }
              oldPrice.setBegin(newPrice.getEnd());
            }
          }
        }
      }
      calculatedPrices.add(newPrice);
    }
    return calculatedPrices;
  }

}
