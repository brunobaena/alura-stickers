public interface Extrator<T> {

	public T extrair(String json) throws ExtratorException;
}
