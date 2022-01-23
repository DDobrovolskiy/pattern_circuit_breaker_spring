package org.example.proxy.impl;

import org.example.proxy.ICashService;
import org.springframework.stereotype.Component;

@Component
public class CashServiceImpl implements ICashService {
    @Override
    public String cash(String value) {
        /*
        Для модуля Cache требуется зависимость resilience4j-cache .
        Инициализация выглядит немного иначе, чем другие модули:

        javax.cache.Cache cache = ...; // Use appropriate cache here
        Cache<Integer, Integer> cacheContext = Cache.of(cache);
        Function<Integer, Integer> decorated
          = Cache.decorateSupplier(cacheContext, () -> service.process(1));
        Здесь кэширование выполняется с помощью используемой реализации  JSR-107 Cache ,
         и Resilience4j предоставляет способ ее применения.

        Обратите внимание, что не существует API для функций декорирования (например , Cache.decorateFunction(Function) ),
        API поддерживает только  типы Supplier и Callable .
         */
        return value;
    }
}
