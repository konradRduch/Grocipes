export class UserData {
    public id: number;
    public name: string;
    public surname: string;
    public email: string;
    public gender: string;
    public birthday: string;
    

    constructor(id: number,name: string,surname: string,email: string,gender: string,birthday: string) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
    }
}