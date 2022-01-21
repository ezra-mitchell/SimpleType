package com.afs.ezra.simpletype.webapp.typing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TypingTestModel {
	
	private String _id;
	private String[] tags;
	private String content;
	private String author;
	private String authorSlug;
	private int length;

}
