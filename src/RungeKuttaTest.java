import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.Callable;

public class RungeKuttaTest {
	
		public final static double H = 0.01;

		public static void main(String[] args) throws FileNotFoundException {
			double yn = 100;
			double xn = 100;
			PrintStream output = new PrintStream(new File("results.csv"));
			for (int i = 0; i < 10000; i++) {
			
			double ky1 = getK('y', yn, xn, 0, 0);
			double kx1 = getK('x', yn, xn, 0, 0);
			double ky2 = getK('y', yn, xn, ky1, kx1);
			double kx2 = getK('x', yn, xn, ky1, kx1);
			double ky3 = getK('y', yn, xn, ky2, kx2);
			double kx3 = getK('x', yn, xn, ky2, kx2);
			double ky4 = getK('y', yn, xn, ky3, kx3);
			double kx4 = getK('x', yn, xn, ky3, kx3);

			double yn1 = yn + (H/6) * (ky1+2*ky2+2*ky3+ky4);
			double xn1 = xn + (H/6) * (kx1+2*kx2+2*kx3+kx4);
			
			yn = yn1;
			xn = xn1;
			output.println(yn +"," + xn1);
			}
		}
		
		public static double getK(char var, double yn, double xn, double ky, double kx) {
			if (var == 'y') {
				return dy(yn + (H/2) * ky, xn + (H/2.0) * ky);
			} else {
				return dx(yn + (H/2) * ky, xn + (H/2.0) * ky);
			}
		}

		public static double dy (double y, double x) {
			return (0.4-0.01*x)*y;
		}
		public static double dx (double y, double x) {
			return (0.005*y-0.3)*x;
		}
}
