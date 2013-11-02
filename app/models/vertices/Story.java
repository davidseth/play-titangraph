package models.vertices;

import com.tinkerpop.frames.Property;

public interface Story extends VertexBase {

//    @Id
//    public Object getId();
    
	@Property("title")
	public String getTitle();

	@Property("title")
	public void setTitle(String name);

	@Property("content")
	public String getContent();

	@Property("content")
	public void setContent(String content);

}
