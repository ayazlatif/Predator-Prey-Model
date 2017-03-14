/**
 * Utilizing the Runge Kutta algorithm to approximate the differential equations,
 * we explore the food chain model.
 * @author AyazLatif
 *
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class RungeKuttaTest {
	
		public final static double H = 0.01;
		public static final double A = 1;
		public static final double B = 1;
		public static final double C = .99;
		public static final double D = .98;
		public static final double E = 1;
		public static final double F = .97;
		public static final double G = .96;

		public static void main(String[] args) throws FileNotFoundException {
			double yn = 100;//A/B;
			double xn = 100;//(((F*C)/E) - ((G*A*C)/(B*E))) / (((F*D)/E) - ((G*A*D)/(B*E)));
			double zn = 100;//((D*xn)/E) - (C/E);
			
			System.out.println(xn + " " + yn + " " + zn);
			
			PrintStream output = new PrintStream(new File("curiousity.csv"));
			for (int steps = 0; steps < 10000; steps++) {
				
				double kx1 = getK('x', xn, yn, zn, 0, 0, 0);
				double ky1 = getK('y', xn, yn, zn, 0, 0, 0);
				double kz1 = getK('z', xn, yn, zn, 0, 0, 0);
				
				double kx2 = getK('x', xn, yn, zn, kx1, ky1, kz1);
				double ky2 = getK('y', xn, yn, zn, kx1, ky1, kz1);
				double kz2 = getK('z', xn, yn, zn, kx1, ky1, kz1);
				
				double kx3 = getK('x', xn, yn, zn, kx2, ky2, kz2);
				double ky3 = getK('y', xn, yn, zn, kx2, ky2, kz2);
				double kz3 = getK('z', xn, yn, zn, kx2, ky2, kz2);
				
				double kx4 = getK('x', xn, yn, zn, kx3, ky3, kz3);
				double ky4 = getK('y', xn, yn, zn, kx3, ky3, kz3);
				double kz4 = getK('z', xn, yn, zn, kx3, ky3, kz3);
				
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
		
		public static double getK(char var, double xn, double yn, double zn, double kx, double ky, double kz) {
			if (var == 'y') {
				return dy(xn + (H/2.0) * ky, yn + (H/2.0) * ky, zn + (H/2.0) * kz);
			} else if(var == 'x'){
				return dx(xn + (H/2.0) * ky, yn + (H/2.0) * ky, zn + (H/2.0) * kz);
			} else {
				return dz(xn + (H/2.0) * ky, yn + (H/2.0) * ky, zn + (H/2.0) * kz);
			}
		}
		
		public static double dx(double x, double y, double z) {
			return A*x - B*x*y;
		}
		
		public static double dy(double x, double y, double z) {
			return -C*y + D*x*y - E*y*z;
		}
		
		public static double dz(double x, double y, double z) {
			return -F*z + G*y*z;
		}
}
