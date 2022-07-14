package synchronized_keyword;

public class SynchronizedExchanger {
	private Object obj = null;

	public synchronized Object getObjSyncMethod() {
		return obj;
	}

	public synchronized void setObjSyncMethod(Object obj) {
		this.obj = obj;
	}

	public Object getObjSyncBlock() {
		synchronized (this) {
			return obj;
		}
	}

	public void setObjSyncBlock(Object obj) {
		synchronized (this.obj){
			this.obj = obj;
		}
	}
}
