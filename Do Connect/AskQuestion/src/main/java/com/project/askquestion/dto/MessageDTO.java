package com.project.askquestion.dto;

/*Author: Shivam Kumar Singh
 * Modified Date :25-08-2022
 * Description : Created dto class for messageDTO
 */

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO
{
	@NotBlank(message = "provide the user Details")
	private String fromUser;

	@NotBlank(message = "provide message")
	private String message;
}