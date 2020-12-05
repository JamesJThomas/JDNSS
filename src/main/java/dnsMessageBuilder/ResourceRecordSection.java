/*
This class represents the resource record sections of a dns message. The answer, authority, and additional sections all have the same format: a fixed number of resource records, such number being determined by the corresponding count field in the header; each record has the format described in the resource record class.
*/
package dnsMessageBuilder;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
public class ResourceRecordSection implements Convertable {
    private Map<Section, List<ResourceRecord> > records;
    public static final int MAX_RECORDS = 65535;
//constructor
    public ResourceRecordSection() {
        records = new EnumMap<Section, List<ResourceRecord> >(Section.class);
        for(Section section: Section.values()) {
            records.put(section, new ArrayList<ResourceRecord>());
        }
    }
    /**
    Adds a resource record to a given section.
    @param section the section to which this resource record should be added
    @param record the resource record to be added to this section.
    @throws TooManyRecordsException if the number of resource records in this section is at least 65535.
    */
    public void add(Section section, ResourceRecord record) throws TooManyRecordsException {
        if(count(section) == MAX_RECORDS) {
            throw new TooManyRecordsException("no more than 65535 records permitted in a resource record section");
        } else {
            records.get(section).add(record);
        }
    }
    /**
    removes a resource record from a given section.
    @param section the section from which a resource record should be removed.
    @param index the integer index at which a record is removed. The semantics of this operation are similar to those of java.util.List.remove() and all other removal operations could be implemented in terms of this operation.
    @returns the record that was removed.
    @throws RecordDoesNotExistException if a removed operation is performed at an invalid index.
    */
    public ResourceRecord remove(Section section, int index) throws RecordDoesNotExistException {
        try {
            return (ResourceRecord)(records.get(section).remove(index));
        } catch(IndexOutOfBoundsException e) {
            throw new RecordDoesNotExistException("record does not exist", e);
        }
    }
//Returns the number of resource records in a given section
    public int count(Section section) {
        return records.get(section).size();
    }
//Converts this resource record section into bytes
    public byte[] toBytes() {
        List<Byte> result = new ArrayList<>();
        Section[] sections = new Section[] {Section.ANSWER, Section.AUTHORITY, Section.ADDITIONAL};
        for(Section section: sections) {
            for(ResourceRecord record: records.get(section)) {
                Utils.addAllBytes(result, record.toBytes());
            }
        }
        return Utils.toByteArray(result);
    }
}
