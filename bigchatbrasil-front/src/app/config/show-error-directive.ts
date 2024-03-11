import { Directive, ElementRef, Input, OnInit, Renderer2 } from "@angular/core";

@Directive({
  selector: '[show-error]'
})
export class ShowErrorDirective implements OnInit {
  @Input('error-message') errorMessage: string;

  constructor(private el: ElementRef, private renderer: Renderer2) { }

  ngOnInit() {
    if (this.errorMessage) {
      this.showError();
    }
  }

  showError() {
    const errorDiv = this.renderer.createElement('div');
    const text = this.renderer.createText(this.errorMessage);
    this.renderer.appendChild(errorDiv, text);
    this.renderer.addClass(errorDiv, 'text-danger');
    this.renderer.appendChild(this.el.nativeElement.parentNode, errorDiv);
  }
}
