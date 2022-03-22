package com.example.transactional.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UpdateDto {
    private String currentName;
    private String updatedName;
}
