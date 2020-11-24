package com.group.api.web.dto;

import com.group.api.domain.Gallery;
import com.group.api.domain.Group;
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
    private Long groupId;

    public GalleryDto(Gallery gallery){
        BeanUtils.copyProperties(gallery,this);
        this.groupId = gallery.getGroup().getId();
    }

    public Gallery toDomain(Group group){
        Gallery gallery = new Gallery();
        BeanUtils.copyProperties(this,gallery);

        gallery.setGroup(group);
        return gallery;
    }

}
