package sonixbp.domain;

@Deprecated
public interface BatchedMutable {
	
	boolean apply();

    void rollback();

}
