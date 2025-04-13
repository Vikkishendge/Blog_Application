package blogapplication.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
	

	String resource;
	String fieldName;
	int fieldValue;
	
	public ResourceNotFoundException(String resource,String fieldName,int fieldValue) {
		super(String.format("%s not found  with  %s : %d",resource,fieldName,fieldValue));
		this.resource = resource;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException(String resource, String fieldName, Integer userId) {
		
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(int fieldValue) {
		this.fieldValue = fieldValue;
	}

	

}
