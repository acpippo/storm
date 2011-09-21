package backtype.storm.topology;

import backtype.storm.generated.StreamInfo;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;
import java.util.HashMap;
import java.util.Map;

public class OutputFieldsGetter implements OutputFieldsDeclarer {
    private Map<Integer, StreamInfo> _fields = new HashMap<Integer, StreamInfo>();

    public void declare(Fields fields) {
        declare(false, fields);
    }

    public void declare(boolean direct, Fields fields) {
        declareStream(Utils.DEFAULT_STREAM_ID, direct, fields);
    }

    public void declareStream(int streamId, Fields fields) {
        declareStream(streamId, false, fields);
    }

    public void declareStream(int streamId, boolean direct, Fields fields) {
        if(_fields.containsKey(streamId)) {
            throw new IllegalArgumentException("Fields for " + streamId + " already set");
        }
        _fields.put(streamId, new StreamInfo(fields.toList(), direct));
    }


    public Map<Integer, StreamInfo> getFieldsDeclaration() {
        return _fields;
    }

}