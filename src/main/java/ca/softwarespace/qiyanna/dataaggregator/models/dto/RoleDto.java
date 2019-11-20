package ca.softwarespace.qiyanna.dataaggregator.models.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
	private int roleId;
	private String name;
}
