package typing.model.constant;


public interface BaseConstant<T> {
	public T prev();
	public T next();
	public T getDefault();
	public int getLength();
	public String[] getStrings();
}
