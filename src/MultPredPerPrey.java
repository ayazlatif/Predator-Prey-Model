import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class MultPredPerPrey {
	
		public final static double H = 0.01;
		public static final double A = .4;
		public static final double B = .01;
		public static final double C = .3;
		public static final double D = .005;
		public static final double E = .01;
		public static final double F = .3;
		public static final double G = .005;

		public static void main(String[] args) throws FileNotFoundException {
			double yn = 100;
			double xn = 100;
			double zn = 100;
			
			PrintStream output = new PrintStream(new File("results2.csv"));
			for (int steps = 0; steps < 10000; steps++) {
				
				double kx1 = getSuperK('x', xn, yn, zn, 0, 0, 0);
				double ky1 = getSuperK('y', xn, yn, zn, 0, 0, 0);
				double kz1 = getSuperK('z', xn, yn, zn, 0, 0, 0);
				
				double kx2 = getSuperK('x', xn, yn, zn, kx1, ky1, kz1);
				double ky2 = getSuperK('y', xn, yn, zn, kx1, ky1, kz1);
				double kz2 = getSuperK('z', xn, yn, zn, kx1, ky1, kz1);
				
				double kx3 = getSuperK('x', xn, yn, zn, kx2, ky2, kz2);
				double ky3 = getSuperK('y', xn, yn, zn, kx2, ky2, kz2);
				double kz3 = getSuperK('z', xn, yn, zn, kx2, ky2, kz2);
				
				double kx4 = getSuperK('x', xn, yn, zn, kx3, ky3, kz3);
				double ky4 = getSuperK('y', xn, yn, zn, kx3, ky3, kz3);
				double kz4 = getSuperK('z', xn, yn, zn, kx3, ky3, kz3);
				
				double xn1 = xn + (H / 6) * (kx1 + 2 * kx2 + 2 * kx3 + kx4);
				double yn1 = yn + (H / 6) * (ky1 + 2 * ky2 + 2 * ky3 + ky4);
				double zn1 = zn + (H / 6) * (kz1 + 2 * kz2 + 2 * kz3 + kz4);
				
				
				if(yn1 < 0) {
					yn1 = 0;
				}
				if(zn1 < 0) {
					zn1 = 0;
				}
				if(xn1 < 0) {
					xn1 = 0;
				}
				
				yn = yn1;
				xn = xn1;
				zn = zn1;
				output.println(xn + "," + yn + "," + zn);
				
				
			}
		}
		
		public static double getSuperK(char var, double xn, double yn, double zn, double kx, double ky, double kz) {
			if (var == 'y') {
				return dy(xn + (H/2.0) * ky, yn + (H/2.0) * ky, zn + (H/2.0) * kz);
			} else if(var == 'x'){
				return dx(xn + (H/2.0) * ky, yn + (H/2.0) * ky, zn + (H/2.0) * kz);
			} else {
				return dz(xn + (H/2.0) * ky, yn + (H/2.0) * ky, zn + (H/2.0) * kz);
			}
		}
		
		public static double dx(double x, double y, double z) {
			return A*x - B*x*y - E*x*z;
		}
		
		public static double dy(double x, double y, double z) {
			return -C*y + D*x*y;
		}
		
		public static double dz(double x, double y, double z) {
			return -F*z + G*x*z;
		}
}
