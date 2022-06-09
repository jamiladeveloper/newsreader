import { Component } from '@angular/core';
import { interval } from 'rxjs';
import { NewsService } from './news.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'rss';

  rssfeed : any;
  configuration : any;
  categories : any;
  selectedCategory : string = '';
  refreshRate : number = 0;
  refreshInterval = interval(2000);
  subscription : any;

  showAddCategory : boolean = false;
  showAddRss : boolean = false;

  constructor(private newsService : NewsService) { }

  ngOnInit(): void {
    this.getConfiguration();
    this.getAllCategories();
    this.getAllFeeds();
  }

  updateCategory(event : any) {
    this.selectedCategory = event.target.value;
    this.rssfeed = [];
    this.subscription.unsubscribe();
    if(this.selectedCategory === "0") {
      this.getAllFeeds();
      this.subscription = this.refreshInterval.subscribe(() => {this.getAllFeeds()});
    }
    else {
      this.getFeedsByCategory();
      this.subscription = this.refreshInterval.subscribe(() => {this.getFeedsByCategory()});
    }
    
  }

  getConfiguration() {
    this.newsService.getConfiguration().subscribe(
      result => {
        this.configuration = result;
        console.log(this.configuration);
        var refreshInt = Number(this.configuration.body.refreshInterval)*1000;
        this.refreshRate = refreshInt;
        this.refreshInterval = interval(refreshInt);
        this.subscription = this.refreshInterval.subscribe(() => {this.getAllFeeds()});
      },
      error => {
        console.log(error);
      }
    );
  }

  getAllFeeds() {
    this.newsService.getAllFeeds().subscribe(
      result => {
        console.log(result.message);
        this.rssfeed = result.body;
      },
      error => {
        console.log(error);
      }
    );
  }

  getAllCategories() {
    this.newsService.getAllCategories().subscribe(
      result => {
        console.log(result.message);
        this.categories = result.body;
      },
      error => {
        console.log(error);
      }
    );
  }

  getFeedsByCategory() {
    
    this.newsService.getFeedByCatId(this.selectedCategory).subscribe(
      result => {
        console.log(result.message);
        this.rssfeed = result.body;
      },
      error => {
        console.log(error);
      }
    );
  }

  openAddCategory() {
    this.showAddCategory = true;
    this.showAddRss = false;
  }

  openAddRss() {
    this.showAddCategory = false;
    this.showAddRss = true;
  }
}
