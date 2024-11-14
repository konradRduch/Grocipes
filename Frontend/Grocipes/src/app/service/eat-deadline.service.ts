import { Injectable } from "@angular/core";
import { EatDeadline } from "../model/eat-deadline.model";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({ providedIn: 'root' })
export class EatDeadlineService {

    eatDeadlineChanged = new BehaviorSubject<EatDeadline[]>([]);
    eatDeadlineChanged$ = this.eatDeadlineChanged.asObservable();

    constructor(
        private http: HttpClient,
      ) { }

    getAllEatDeadline(): Observable<EatDeadline[]>{
        return this.http.get<EatDeadline[]>('http://localhost:8080/eatDeadline/getAllEatDeadline').pipe(
            tap(data => {
              this.eatDeadlineChanged.next(data);
            })
          )
    }

    getEatDeadline(id: number): Observable<EatDeadline>{
        return this.http.get<EatDeadline>(`http://localhost:8080/eatDeadline/${id}`);
    }

    addEatDeadline(dto: any){
        const addDto = {
            recipeId: dto.recipeId,
            eatingDate: dto.eatingDate,
            done: dto.done,
            rate: dto.rate,
            comment: dto.comment
        }
        return this.http.post('http://localhost:8080/eatDeadline/add',addDto)
    }

    /*
     fetchGroceries(): Observable<Product[]> {
    return this.http.get<Product[]>('http://localhost:8080/products').pipe(
      tap(products => {
        console.log('From service');
        console.log(products);
        this.groceriesChanged.next(products);
      })
    )
  }

  getProduct(id: number): Observable<Product>{
    return this.http.get<Product>(`http://localhost:8080/products/${id}`);
  }

  addProduct(product: Product) {
    console.log(product);

    // Stwórz obiekt zgodny z wymaganą strukturą JSON
    const payload = {
      productDTO: {
        name: product.name,
        weight: product.weight,
        price: product.price,
        image_url: product.image_url,
        calories: product.calories
      },
      nutritionFactNutrientDTO: product.nutrient.map(nutrient => ({
        nutrientId: nutrient.id,
        amount: nutrient.amount,
        dailyValue: nutrient.dailyValue
      }))
    };

    console.log(payload);
    // Wyślij zapytanie POST z utworzonym obiektem payload
    return this.http.post('http://localhost:8080/products/add', payload);
  } 
    */
}