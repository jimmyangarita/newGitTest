import { Component } from '@angular/core';

import { ServiceComponent } from './service';

@Component({
    moduleId: module.id,
    selector: 'services-di-app',
    templateUrl: 'services-di.component.html',
    styleUrls: ['services-di.component.css'],
    directives: [ServiceComponent]
})
export class ServicesDiAppComponent {
}
