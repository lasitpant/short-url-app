import { Component, OnInit } from '@angular/core';
import { Url } from "./url";


import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UrlService} from "../url.service";
import {Stats} from "./stats";


@Component({
  selector: 'app-short-url',
  templateUrl: './short-url.component.html',
  styleUrls: ['./short-url.component.scss']
})
export class ShortUrlComponent implements OnInit {
  generatedLink: string;
  reg = '(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?';
  urlForm: FormGroup;

  public pieChartColors:Array<any> = [
    {
      backgroundColor: [
        'lightblue',
        'magenta',
        'blue',
        'orange',
        'pink'
      ]
    }
  ];

  public doughnutChartColors:Array<any> = [
    {
      backgroundColor: [
        'green',
        'blue',
        'orange',
        'yellow',
        'black'
      ]
    }
  ];

  public barChartLabels: string[] = [];
  public barChartLegend = true;
  public barChartPlugins = [];

  public barChartData: any[] = [
    { data: [], label: 'Operating Systems' },

  ];

  public doughnutChartLabels:string[] = [];
  public demodoughnutChartData:number[] = [];
  public doughnutChartType:string = 'doughnut';

  constructor(public service: UrlService) {
  }
  public isVisible: boolean = false;

  pieChartLabels: string[] = [];
  pieChartData: number[] = [];

  ngOnInit(): void {
    this.urlForm = new FormGroup({
      'url': new FormControl(null, [
        Validators.required,
        Validators.pattern(this.reg),

      ]),

      'expiryDate': new FormControl()
    });

    this.getBrowserDataStats();
    this.getDeviceDataStats();
    this.getOperatingSystemDataStats();
  }


  get url() { return this.urlForm.get('url'); }


  getBrowserDataStats(){
    this.pieChartData = []
    this.pieChartLabels =[]
    this.service.getBrowserStats().subscribe((data: Stats[])=>{

      data.forEach(val => {
        this.pieChartLabels.push(val.name);
        this.pieChartData.push(val.total);
        }
      );
    })
  }
  getDeviceDataStats(){
    this.demodoughnutChartData=[];
    this.doughnutChartLabels = []
    this.service.getDeviceStats().subscribe((data: Stats[])=>{
      console.log(data);
      data.forEach(val => {
          this.doughnutChartLabels.push(val.name);
          this.demodoughnutChartData.push(val.total);
        }
      );
    })
  }

  getOperatingSystemDataStats(){
    this.barChartLabels = [];
    this.barChartData[0]['data'] = [];
    this.service.getOsStats().subscribe((data: Stats[])=>{
      console.log(data);
      data.forEach(val => {
          this.barChartLabels.push(val.name);
          this.barChartData[0]['data'].push(val.total);
        }
      );
    })
  }

  onSubmit() {
    // console.log(this.urlForm.value);
    const payload = new Url();
    payload.longUrl = this.urlForm.value['url'];
    payload.expiryDate = this.urlForm.value['expiryDate'];
    payload.createdOn = new Date();

    this.service.generateShortLink(payload).subscribe((data:string)=>{
      this.generatedLink = data['shortUrl'];
      this.isVisible = true;
    });

    this.urlForm.reset();

  }

  updateDash(){
    this.getBrowserDataStats();
    this.getDeviceDataStats();
    this.getOperatingSystemDataStats();
  }



}
