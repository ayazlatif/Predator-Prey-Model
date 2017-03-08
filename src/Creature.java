public class Creature {
   public double trueBirthRate;
   public double trueDeathRate;
   public double[] consumptionRate; // because mult specs
   public double[] growthByConsumptionRate; // because mult spec
   public double population;
   public String animal;
   
   public Creature(double trueBirthRate, double trueDeathRate, double[] consumptionRate,
                   double[] growthByConsumptionRate, int population, String animal) {
      this.trueBirthRate = trueBirthRate;
      this.trueDeathRate = trueDeathRate;
      this.consumptionRate = consumptionRate; // because mult specs
      this.growthByConsumptionRate = growthByConsumptionRate; // because mult spec
      this.population = population;  
      this.animal = animal;             
   }
   
   public String toString() {
      return animal;
   }
}