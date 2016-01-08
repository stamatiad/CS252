package stratego.model;

import java.util.ArrayList;
import java.util.List;

public class Scout extends MovablePlayerToken implements Token {

	/**
	 * <b>post-condition</b>: Constructs Dragon Player Token
	 * @param own owner of the token
	 */
	public Scout(Object own){
		super("Scout", own);
		this.rank = 2;
	}
	

}
