package ri.iit.benchmark.spring.data.solr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        System.out.println("Spring initialized");

        System.out.println("Bulk test, batch size exploration");
        performBulkTest(applicationContext, 100);
        performBulkTest(applicationContext, 1000);
        performBulkTest(applicationContext, 10000);
        performBulkTest(applicationContext, 100000);
        performBulkTest(applicationContext, 1000000);
        performBulkTest(applicationContext, 10000000);
    }

    private static void performTest(ApplicationContext applicationContext, int batchSize) {
        performSequentialTest(applicationContext, batchSize);
        performBulkTest(applicationContext, batchSize);
    }

    private static void performSequentialTest(ApplicationContext applicationContext, int batchSize) {
        System.out.println("Test, batch size: " + batchSize);
        System.out.println("=== Sequential test timing ===");
        long time = System.currentTimeMillis();
        sequentialUpdate(applicationContext, batchSize);
        time = System.currentTimeMillis() - time;
        System.out.println("Time: " + time + "ms\n");
    }

    private static void performBulkTest(ApplicationContext applicationContext, int batchSize) {
        System.out.println("=== Bulk test timing ===");
        System.out.println("Batch size: " + batchSize);
        long time = System.currentTimeMillis();
        bulkUpdate(applicationContext, batchSize);
        time = System.currentTimeMillis() - time;
        System.out.println("Time: " + time + "ms\n");
    }

    private static void sequentialUpdate(ApplicationContext applicationContext, int batchSize) {
        System.out.println("Sequential update benchmark Start");
        for(int i = 0; i < batchSize; i++) {
            applicationContext.getBean(ExpBeanRepository.class).save(formBean());
        }
    }

    private static void bulkUpdate(ApplicationContext applicationContext, int batchSize) {
        System.out.println("Bulk update benchmark Start");
        Collection<SimpleSolrExperimentationBean> beansCollection = new ArrayList<SimpleSolrExperimentationBean>();
        for(int i = 0; i < batchSize; i++) {
            beansCollection.add(formBean());
        }
        applicationContext.getBean(ExpBeanRepository.class).save(beansCollection);
        System.out.println("");
    }

    private static SimpleSolrExperimentationBean formBean() {
        SimpleSolrExperimentationBean bean = new SimpleSolrExperimentationBean();
        bean.setId(new Random().nextLong() + "");
        bean.setText(new Random().nextLong() + "");
        return bean;
    }
}
