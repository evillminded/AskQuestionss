package com.project.askquestion.dto;

/*Author: Nahush
 * Modified Date :25-08-2022
 * Description : Created dto class for responseDTO
 */

//import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ResponseDTO {

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ResponseDTO(String msg) {
		super();
		this.msg = msg;
	}

	public ResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}