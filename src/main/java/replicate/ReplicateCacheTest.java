package replicate;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReplicateCacheTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"default-pageCache-context.xml");

		CacheManager cacheManager = (CacheManager) applicationContext
				.getBean("pageCacheEhcacheManager");

		Cache cache = cacheManager.getCache("webPageCache");
		
		List<String> list  = cache.getKeys();
		if(list!=null && list.size()>0){
			for (String string : list) {
				Element element = cache.get(string);
				System.out.println(element.getValue());
			}
		}
		
		synchronized (ReplicateCacheTest.class) {
			try {
				ReplicateCacheTest.class.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
