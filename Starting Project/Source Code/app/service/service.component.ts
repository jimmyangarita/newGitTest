import { Component } from '@angular/core';

import { CmpAComponent } from './cmp-a.component';
import { CmpBComponent } from './cmp-b.component';

@Component({
    moduleId: module.id,
    selector: 'si-service',
    template: `
    <si-cmp-a></si-cmp-a>
    <si-cmp-b></si-cmp-b>
  `,
    directives: [CmpAComponent, CmpBComponent]
})
export class ServiceComponent {
}
