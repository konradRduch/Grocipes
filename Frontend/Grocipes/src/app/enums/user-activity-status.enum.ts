export enum UserActivityStatus {
    LOW_ACTIVE = 'Low active',
    MODERATELY_ACTIVE = 'Moderately active',
    HIGH_ACTIVE = 'High active'
  }
  
  // Przestrzeń nazw z metodami pomocniczymi związanymi z enumem
  export namespace UserActivityStatus {
    export function getRange(status: UserActivityStatus): [number, number] {
        switch (status) {
          case UserActivityStatus.LOW_ACTIVE:
            return [1.4, 1.69];
          case UserActivityStatus.MODERATELY_ACTIVE:
            return [1.7, 1.99];
          case UserActivityStatus.HIGH_ACTIVE:
            return [2.0, 2.4];
          default:
            throw new Error('Unknown status');
        }
      }
    
      // Metoda do pobierania nazw statusów
      export function getDisplayName(status: UserActivityStatus): string {
        return status;
      }
    
      // Metoda do pobrania wszystkich statusów
      export function getAllStatuses(): UserActivityStatus[] {
        return [
            UserActivityStatus.LOW_ACTIVE,
            UserActivityStatus.MODERATELY_ACTIVE,
            UserActivityStatus.HIGH_ACTIVE
          ];
      }
  }