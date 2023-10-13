package br.com.bshiromoto.todolist;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
  public static void copyNonNullProperties(Object source, Object target) {
    // BeanUtils é uma classe do Spring que tem um método que copia as propriedades não nulas de um objeto source para um objeto target
    BeanUtils.copyProperties(source, target, getNullProperties(source));
  }

  public static String[] getNullProperties(Object source) {
    // BeanWrapper é uma interface do Spring que permite manipulação de propriedades de um objeto JavaBean de forma flexível, sem precisar chamar os getters e setters
    // BeanWrapperImpl é a implementação concreta da interface
    final BeanWrapper src = new BeanWrapperImpl(source);

    // o método getPropertyDescriptors vem da interface BeanWrapper - usado para interagir com os atributos de um objeto de forma dinâmica
    PropertyDescriptor[] propertyDescriptors = src.getPropertyDescriptors();

    // criado um Set chamado emptyNames que armazena o nome das propriedades com valor nulo
    Set<String> emptyNames = new HashSet<>();

    // itera sobre as propriedades e para cada propriedade (propertyDescriptor) que tiver seu valor null, o nome dessa propriedade é salvo no Set emptyNames
    for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
      Object srcValue = src.getPropertyValue(propertyDescriptor.getName());

      if(srcValue == null) {
        emptyNames.add(propertyDescriptor.getName());
      }
    }

    // um array de strings é criado com o tamanho igual ao número de elementos de emptyNames
    String[] result = new String[emptyNames.size()];

    // os nomes das propriedades são retornados no array result
    return emptyNames.toArray(result);
  }
}
