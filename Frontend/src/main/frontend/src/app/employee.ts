export class Employee {
    //It should be same as the Employee entity
    id!:number ;
    fullName!: string ;
    dateOfJoin!: string;
    address!: string;
    email!: string;
    dateOfBirth!: string;
    workSchedule!: string;
    role !: string;
    onLeave!: string;
    worksIn!: string;
    profilePhoto!: File;
    profilePhotoPath!: string;
    projectList!: Set<string>;
    dependentList!: Set<string>;
}
