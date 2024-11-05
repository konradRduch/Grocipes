export class Progress {
    startWeight: number;
    startAbdominalCircumference: number;
    startBodyFatLevel: number;
    currentWeight: number;
    currentAbdominalCircumference: number;
    currentBodyFatLevel: number;
    targetWeight: number;
    targetAbdominalCircumference: number;
    targetBodyFatLevel: number;

    constructor(
        startWeight: number, startAbdominalCircumference: number, startBodyFatLevel: number, currentWeight: number, currentAbdominalCircumference: number, currentBodyFatLevel: number, targetWeight: number, targetAbdominalCircumference: number, targetBodyFatLevel: number
    ) {
        this.startWeight = startWeight;
        this.startAbdominalCircumference = startAbdominalCircumference;
        this.startBodyFatLevel = startBodyFatLevel;
        this.currentWeight = currentWeight;
        this.currentAbdominalCircumference = currentAbdominalCircumference;
        this.currentBodyFatLevel = currentBodyFatLevel;
        this.targetWeight = targetWeight;
        this.targetAbdominalCircumference = targetAbdominalCircumference;
        this.targetBodyFatLevel = targetBodyFatLevel;
    }
}