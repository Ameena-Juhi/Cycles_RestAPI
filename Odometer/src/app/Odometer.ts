export class Odometer{

        private static readonly DIGITS = "123456789";
        private reading: number;
        private msg: string | undefined;
      
        private static getMinReading(size: number): number {
          return parseInt(Odometer.DIGITS.substring(0, size));
        }
      
        private static getMaxReading(size: number): number {
          return parseInt(Odometer.DIGITS.substring(Odometer.DIGITS.length - size));
        }
      
        private static getSize(reading: number): number {
          return reading.toString().length;
        }
      
        constructor(size: number) {
          this.reading = Odometer.getMinReading(size);
        }
      
        public getReading(): number {
          return this.reading;
        }

        public getMsg():string | undefined{
            return this.msg;
        }
      
        public setReading(reading: number): void {
          if (!Odometer.isAscending(reading)) {
            this.msg='The reading is not in ascending order';
            reading++;
          } else {

            this.reading = reading;
          }
        }
      
        public toString(): string {
          return `(${this.reading})`;
        }
      
      
        public static isAscending(reading: number): boolean {
          if (reading < 10) {
            return true;
          }
          if (reading % 10 <= Math.floor(reading / 10) % 10) {
            return false;
          }
          return Odometer.isAscending(Math.floor(reading / 10));
        }
      
        public incrementReading(): void {
          do {
            if (this.reading === Odometer.getMaxReading(this.getSize())) {
              this.reading = Odometer.getMinReading(this.getSize());
            } else {
              this.reading++;
            }
          } while (!Odometer.isAscending(this.reading));
        }
      
        public decrementReading(): void {
          do {
            if (this.reading === Odometer.getMinReading(this.getSize())) {
              this.reading = Odometer.getMaxReading(this.getSize());
            } else {
              this.reading--;
            }
          } while (!Odometer.isAscending(this.reading));
        }
      
        public reset(): void {
          this.reading = Odometer.getMinReading(this.getSize());
        }
      
        public getSize(): number {
          return Odometer.getSize(this.reading);
        }
      
      
    
}