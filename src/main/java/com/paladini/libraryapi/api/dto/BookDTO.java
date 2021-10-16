package com.paladini.libraryapi.api.dto;

import lombok.*;

@Data
@ToString@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
}
