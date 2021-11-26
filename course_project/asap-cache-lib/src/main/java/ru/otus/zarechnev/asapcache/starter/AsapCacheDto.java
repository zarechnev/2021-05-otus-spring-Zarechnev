package ru.otus.zarechnev.asapcache.starter;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AsapCacheDto {
    private Object value;
}
