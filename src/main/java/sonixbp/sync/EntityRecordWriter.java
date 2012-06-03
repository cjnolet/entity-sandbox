package sonixbp.sync;

import cloudbase.core.client.Connector;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import sonixbp.domain.BasicEntity;
import sonixbp.service.EntityService;

import java.io.IOException;

public class EntityRecordWriter implements RecordWriter<Text, BasicEntity>{

    private EntityService entityService;

    public EntityRecordWriter(EntityService entityService) {

        this.entityService = entityService;
    }

    @Override
    public void write(Text text, BasicEntity basicEntity) throws IOException {

        entityService.save(basicEntity);
    }

    @Override
    public void close(Reporter reporter) throws IOException {}
}
