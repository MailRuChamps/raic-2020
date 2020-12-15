package model;

import util.StreamUtil;

public class Action {
    private java.util.Map<Integer, model.EntityAction> entityActions;
    public java.util.Map<Integer, model.EntityAction> getEntityActions() { return entityActions; }
    public void setEntityActions(java.util.Map<Integer, model.EntityAction> entityActions) { this.entityActions = entityActions; }
    public Action() {}
    public Action(java.util.Map<Integer, model.EntityAction> entityActions) {
        this.entityActions = entityActions;
    }
    public static Action readFrom(java.io.InputStream stream) throws java.io.IOException {
        Action result = new Action();
        int entityActionsSize = StreamUtil.readInt(stream);
        result.entityActions = new java.util.HashMap<>(entityActionsSize);
        for (int i = 0; i < entityActionsSize; i++) {
            int entityActionsKey;
            entityActionsKey = StreamUtil.readInt(stream);
            model.EntityAction entityActionsValue;
            entityActionsValue = model.EntityAction.readFrom(stream);
            result.entityActions.put(entityActionsKey, entityActionsValue);
        }
        return result;
    }
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, entityActions.size());
        for (java.util.Map.Entry<Integer, model.EntityAction> entityActionsEntry : entityActions.entrySet()) {
            int entityActionsKey = entityActionsEntry.getKey();
            model.EntityAction entityActionsValue = entityActionsEntry.getValue();
            StreamUtil.writeInt(stream, entityActionsKey);
            entityActionsValue.writeTo(stream);
        }
    }
}
