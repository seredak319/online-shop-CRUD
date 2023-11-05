package pl.edu.pw.mwo_lab2_crud_shop.entity.dictionary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    NEW("NEW"),
    PROCESSING("PROCESSING"),
    FINISHED("FINISHED");

    private final String referenceName;
}
