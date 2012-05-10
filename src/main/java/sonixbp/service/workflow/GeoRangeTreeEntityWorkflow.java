package sonixbp.service.workflow;

import sonixbp.domain.*;
import sonixbp.exception.MissingRootNodeException;
import sonixbp.schema.EntitySchema;
import sonixbp.service.EntityService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GeoRangeTreeEntityWorkflow {

    EntitySchema schama;

    public static final String DEFAULT_TYPE = "geoRangeNode";

    BasicEntity root;

    Collection<BasicEntity> currentSiblings;

    EntityService entityService;
    public GeoRangeTreeEntityWorkflow(EntityService entityService, String rangeTreeId, boolean createIfEmpty)
            throws MissingRootNodeException {

        this.entityService = entityService;

        // first, query the root node to find out if it exists
        if(root == null) {
           root = entityService.getByTypeAndId(DEFAULT_TYPE, rangeTreeId);

            if(root == null && createIfEmpty) {
                createRootNode(rangeTreeId);
            }

            else if(root == null) {
                throw new MissingRootNodeException();
            }
        }
    }

    public void addNodeToRangeTree(String rootNodeId, StructuredEntity node) {

        // first, get the range that we can traverse
        Attribute minX = node.getSingleAttribute("minX");
        Attribute minY = node.getSingleAttribute("minY");
        Attribute maxX = node.getSingleAttribute("maxX");
        Attribute maxY = node.getSingleAttribute("maxY");




    }

    private void createRootNode(String id) {

        root = new Entity(id, DEFAULT_TYPE);
        entityService.save(root);
    }

    private Collection<BasicEntity> followChildren(Entity node) {

        List<String> ids = new ArrayList<String>();
        List<String> types = new ArrayList<String>();
        Collection<Relationship> children = node.getFullRelationship("child");

        for(Relationship child : children) {
            if(child.getType().equals(DEFAULT_TYPE)) {
                ids.add(child.getId());
                types.add(DEFAULT_TYPE);
            }
        }

        return entityService.getAllByTypesAndIds((String[])types.toArray(), (String[])ids.toArray());
    }

    public Collection<BasicEntity> findNodesInRange(NodeRange range) {
        return null;

    }
}