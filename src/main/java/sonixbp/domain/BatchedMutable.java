package sonixbp.domain;


public interface BatchedMutable {
	
	boolean apply();

    void rollback();

}
