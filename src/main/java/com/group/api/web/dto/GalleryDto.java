package com.group.api.web.dto;

import com.group.api.domain.Gallery;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GalleryDto implements Serializable {
    private Long id;
    private String title;
    private String filePath;

    public GalleryDto(Gallery gallery){
        BeanUtils.copyProperties(gallery,this);
    }

    public Gallery toDomain(){
        Gallery gallery = new Gallery();
        BeanUtils.copyProperties(this,gallery);

        return gallery;
    }

}
