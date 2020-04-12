package actions;

import shapes.MyShape;

public class DeleteAction extends MyShape {
    MyShape myShape;

    public DeleteAction(MyShape myShape) {
        this.myShape = myShape;
    }

    public MyShape getMyShape(){
        return myShape;
    }
}
