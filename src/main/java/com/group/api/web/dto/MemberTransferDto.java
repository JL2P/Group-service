package com.group.api.web.dto;

import com.group.api.domain.Member;
import lombok.*;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberTransferDto {
    private Long id;
    private String manager;

    public MemberTransferDto(Member member){ BeanUtils.copyProperties(member,this);}

    public Member toEntity(Member member){
        member.setId(this.id);
        member.setAccountId(member.getAccountId());
        member.setConfirm(member.getConfirm());
        member.setDate(member.getDate());
        member.setManager(this.getManager());
        member.setGroup(member.getGroup());

        return member;
    }
}
