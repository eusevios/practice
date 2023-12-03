package functions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({

        @JsonSubTypes.Type(value = ArrayTabulatedFunction.class, name = "array"),
        @JsonSubTypes.Type(value = LinkedListTabulatedFunction.class, name = "llist"),

})

public interface TabulatedFunction extends MathFunction, Iterable<Point> {

    int getCount();

    double getX(int index);

    double getY(int index);

    void setY(int index, double value);

    int indexOfX(double x);

    int indexOfY(double y);

    double leftBound();

    double rightBound();

}
