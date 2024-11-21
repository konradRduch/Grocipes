export enum ProductUnit {
    GRAM = 1,
    MILLIGRAM,
    LITER,
    KILOGRAM,
    MILLILITER,
    PACK,
    CAN,
    BOTTLE,
    JAR,
    CARTON,
    CRATE,
    BARREL
}

export namespace ProductUnit {
    // Function to check if the unit is a mass unit (e.g., grams, kilograms)
    export function isMass(unit: ProductUnit): boolean {
        return unit === ProductUnit.GRAM || unit === ProductUnit.KILOGRAM || unit === ProductUnit.MILLIGRAM;
    }

    // Function to return the short label for the unit (e.g., "g" for GRAM)
    export function toLabel(unit: ProductUnit): string {
        switch (unit) {
            case ProductUnit.GRAM: return "g";
            case ProductUnit.MILLIGRAM: return "mg";
            case ProductUnit.LITER: return "L";
            case ProductUnit.KILOGRAM: return "kg";
            case ProductUnit.MILLILITER: return "ml";
            case ProductUnit.PACK: return "pack";
            case ProductUnit.CAN: return "can";
            case ProductUnit.BOTTLE: return "bottle";
            case ProductUnit.JAR: return "jar";
            case ProductUnit.CARTON: return "carton";
            case ProductUnit.CRATE: return "crate";
            case ProductUnit.BARREL: return "barrel";
            default: return "Unknown"; // In case the unit doesn't exist
        }
    }

    // Function to generate options for a select input (dropdown)
    export function getSelectOptions(): { value: ProductUnit, label: string }[] {
        return Object.keys(ProductUnit)
            .filter(key => isNaN(Number(key)))  // Filter out numeric keys (from bidirectional enum)
            .map(key => {
                // We get the unit using the enum key
                const unit = ProductUnit[key as keyof typeof ProductUnit];
                
                // Ensure unit is actually a ProductUnit (not a method)
                if (typeof unit === 'number') {
                    return {
                        value: unit,
                        label: toLabel(unit)
                    };
                }
                // If not a valid unit, return nothing (although it should always be valid)
                return null;
            })
            .filter(option => option !== null) as { value: ProductUnit, label: string }[];  // Type assert that options are valid
    }

    // Function to get the numeric value of a unit
    export function getUnitIndex(unit: ProductUnit): number {
        return unit; // Directly returns the numeric value of the enum
    }






    
}
