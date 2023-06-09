import java.awt.geom.Rectangle2D;

/**
 * This class is a subclass of FractalGenerator.  It is used to compute the
 * Burning Ship fractal.
 */
public class BurningShip extends FractalGenerator
{
    /**
     * A constant for the number of maximum iterations.
     */
    public static final int MAX_ITERATIONS = 2000;
    
    /**
     * This method allows the fractal generator to specify which part
     * of the complex plane is the most interesting for a fractal.
     * It is passed a rectangle object and the method modifies the
     * rectangle's fields to show the correct initial range for the fractal.
     * This implementation sets the initial range to x=-2, y=-2.5, 
     * width=height=4.
     */
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }
    
    /**
     * This method implements the iterative function for the Tricorn fractal.
     * It takes two doubles for the real and imaginary parts of the complex
     * plane and returns the number of iterations for the corresponding
     * coordinate.
     */
    public int numIterations(double x, double y)
    {
        /** Start with iterations at 0. */
        int iteration = 0;
        /** Initialize zreal and zimaginary. */
        double zreal = 0;
        double zimaginary = 0;
        
        /**
         * Compute Zn = (abs[Re(zn-1)] + i(abs[img(zn-1)]))^2 + c where values 
         * are complex numbers represented by zreal and zimaginary, Z0=0, and
         * c is the particular point in the fractal that we are displaying
         * (given by x and y).  It is iterated until Z^2 > 4 (absolute value
         * of Z is greater than 2) or maximum number of iterations is reached.
         */
        while (iteration < MAX_ITERATIONS &&
               zreal * zreal + zimaginary * zimaginary < 4)
        {
            double zrealUpdated = zreal * zreal - zimaginary * zimaginary + x;
            double zimaginaryUpdated = 2 * Math.abs(zreal)
            * Math.abs(zimaginary) + y;
            
            zreal = zrealUpdated;
            zimaginary = zimaginaryUpdated;
            
            iteration += 1;
        }
        
        /**
         * If the number of maximum iterations is reached, return -1 to
         * indicate the point didn't escape outside of the boundary.
         */
        if (iteration == MAX_ITERATIONS)
        {
            return -1;
        }
        
        return iteration;
    }
    
    /** 
     * An implementation of toString() on this fracal implementation.  Returns
     * the name of the fractal: "Burning Ship".
     */
    public String toString() {
        return "Burning Ship";
    }
    
}